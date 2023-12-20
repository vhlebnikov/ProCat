package main

import (
	"context"
	"fmt"
	"github.com/jackc/pgx/v5"
	"github.com/joho/godotenv"
	"net/http"
	"os"
)

func connectToDB() *pgx.Conn {
	if err := godotenv.Load(); err != nil {
		panic("No .env file found")
	}

	url := os.Getenv("DATABASE_URL")

	conn, err := pgx.Connect(context.Background(), url)
	if err != nil {
		fmt.Fprintf(os.Stderr, "Unable to connect to database: %v\n", err)
		os.Exit(1)
	}

	return conn
}

func handleFunc() {
	mux := http.NewServeMux()
	mux.HandleFunc("/login/", login)
	mux.HandleFunc("/items/", getItems)
	err := http.ListenAndServe(":8080", mux)
	if err != nil {
		panic("Problems with connection")
	}
}

func main() {
	handleFunc()
}
