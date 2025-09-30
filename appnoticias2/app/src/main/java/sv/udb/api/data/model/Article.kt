package sv.edu.api.data.model

data class Article(
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val url: String?   // Aqui se agrega para que reconozca la NewsAdapter que el titulo sea la url
)
