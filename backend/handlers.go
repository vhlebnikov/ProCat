package main

import (
	"context"
	"encoding/json"
	"fmt"
	"net/http"
)

type User struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

type Item struct {
	Id             int    `json:"id"`
	Name           string `json:"name"`
	Description    string `json:"description"`
	Specifications string `json:"specifications"`
	Price          int    `json:"price"`
}

type Items struct {
	Id          int    `json:"id"`
	Name        string `json:"name"`
	Description string `json:"description"`
	Price       int    `json:"price"`
}

func makeResponse(data any) []byte {
	jsonResp, err := json.Marshal(data)
	if err != nil {
		panic(fmt.Sprintf("Error happened in JSON marshal. Err: %s", err))
	}
	return jsonResp
}

func login(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodPost {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}
	w.Header().Set("Content-Type", "application/json")

	var user User
	json.NewDecoder(r.Body).Decode(&user)

	if user.Username == "" || user.Password == "" {
		data := make(map[string]string)
		data["message"] = "Not enough data"
		_, err := w.Write(makeResponse(data))
		if err != nil {
			panic("Something happened when login")
		}
		return
	}

	var passDB string

	conn := connectToDB()

	defer conn.Close(context.Background())

	err := conn.QueryRow(context.Background(),
		fmt.Sprintf("SELECT password FROM users WHERE username='%s'", user.Username)).Scan(&passDB)
	if err != nil {
		data := make(map[string]string)
		data["message"] = "No such user"
		_, err := w.Write(makeResponse(data))
		if err != nil {
			panic("Something happened when login")
		}
		return
	}

	if passDB != user.Password {
		data := make(map[string]string)
		data["message"] = "Wrong password"
		_, err := w.Write(makeResponse(data))
		if err != nil {
			panic("Something happened when login")
		}
		return
	}

	data := make(map[string]string)
	data["message"] = "OK"
	_, err = w.Write(makeResponse(data))
	if err != nil {
		panic("Something happened when login")
	}
	return
}

func getItems(w http.ResponseWriter, r *http.Request) {
	if r.Method != http.MethodGet {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}
	w.Header().Set("Content-Type", "application/json")

	id := r.URL.Query().Get("id")

	if id == "" {
		conn := connectToDB()
		defer conn.Close(context.Background())

		var items []Items
		rows, err := conn.Query(context.Background(), "SELECT id, name, description, price FROM items")
		if err != nil {
			panic("Query error")
		}
		defer rows.Close()
		for rows.Next() {
			var item Items
			err := rows.Scan(&item.Id, &item.Name, &item.Description, &item.Price)
			if err != nil {
				panic("Can't receive value from row")
			}

			items = append(items, item)
		}

		data := make(map[string][]Items)
		data["items"] = items

		_, err = w.Write(makeResponse(data))
		if err != nil {
			panic("Something happened when getItems")
		}
	} else {
		conn := connectToDB()
		defer conn.Close(context.Background())

		var item Item
		err := conn.QueryRow(context.Background(),
			fmt.Sprintf("SELECT id, name, description, specifications, price FROM items WHERE id='%s'", id)).
			Scan(&item.Id, &item.Name, &item.Description, &item.Specifications, &item.Price)
		if err != nil {
			data := make(map[string]string)
			data["message"] = "No such item"
			_, err := w.Write(makeResponse(data))
			if err != nil {
				panic("Something happened when getItems")
			}
			return
		}

		_, err = w.Write(makeResponse(item))
		if err != nil {
			panic("Something happened when getItems")
		}
	}
}
