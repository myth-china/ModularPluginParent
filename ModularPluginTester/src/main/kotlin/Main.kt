import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


fun main() {
    println("Hello World!")
    Observable.just("From rxjava just.").subscribe {
        println(it)
    }

    println(run("https://www.baidu.com"))
}

val client = OkHttpClient()

@Throws(IOException::class)
fun run(url: String): String {
    val request: Request = Request.Builder()
        .url(url)
        .build()
    client.newCall(request).execute().use { response -> return response.body?.string().toString() }
}