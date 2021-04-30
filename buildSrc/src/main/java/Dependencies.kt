object Dependencies {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val okHttp3LoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    const val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutineAdapter}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    const val daggerHiltGradle =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val hiltLifecycleViewModel =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleViewModel}"
    const val hiltNavigation =
        "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigation}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"

    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
    const val lifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}"

    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestDependencies {
    const val junit = "junit:junit:${TestVersions.junit}"
    const val extJunit = "androidx.test.ext:junit:${TestVersions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"

}