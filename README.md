# 安卓APP网络开发参考 [👉代码](https://github.com/Chiu-xaH/My-Standard-Android-Network/blob/be4602b5457b2565a2aa65bdbac8ce61b319d9d6/app/src/main/java/com/example/pagingsample)
Flow取代LiveData 、协程 、Compose、Retrofit与OkHttp、Gson、Jsoup

KMP可以用Ktor取代Retrofit与OkHttp、Kotlin-Serialize取代Gson，可以看[👉这里](https://github.com/Chiu-xaH/Blog/blob/a7f1b25d056c1683dc2f864782fc8e0437981087/Blog-KMP/composeApp/src/commonMain/kotlin/org/chiuxah/blog)，不知道结构对不对，反正能跑

我很后悔当初刚学完安卓开发，使用LiveData技术写项目，聚在工大若干个接口全是这么写的，而且当时对协程掌握程度不够，造成一些性能上的损失，现在项目比较庞大，重构需要慢慢来，希望能够完成吧；还有SharePrefs，早知道用DataStore了，现在项目庞大，要修改很多处，可能不会改了；还有没有用Room而是直接写数据库连接，这里可以重构，用的比较少