package com.example.procattemplate

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.HttpHeaderParser
import com.example.procattemplate.data_storage.DataCoordinator
import com.example.procattemplate.data_storage.DataCoordinator.Companion.identifier
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

fun DataCoordinator.sampleAPI(
    url: String,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    val apiRequestQueue = this.apiRequestQueue ?: return
    /// Create the payload
    val payload = JSONObject()
    payload.put("email", "test@gmail.com")
    /// Create the Headers
    val headers: MutableMap<String, String> = HashMap<String, String>()
    headers["a-header-key"] = "a-sample-key"
    // Create the Request
    val request = GsonRequest(
        url = url,
        clazz = Response::class.java,
        method = Request.Method.GET,
        headers = headers,
        jsonPayload = payload,
        listener = {
            Log.i(
                identifier,
                "request : $it.",
            )
            onSuccess()
        },
        errorListener = {
            val response = it.networkResponse
            try {
                val errorJson = String(
                    response.data,
                    Charset.forName(HttpHeaderParser.parseCharset(response.headers))
                )
                val errorObj = Gson().fromJson(errorJson, Error::class.java)
                Log.i(
                    identifier,
                    " request : ${errorObj.error}",
                )
                onError()
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
            }
        })
    // Make the request
    apiRequestQueue.add(request)
}