package com.example.flashcard.data.remote

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.flashcard.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/*перед этим дейсвтием я совершил следующие:
* 1. Так как токен не должен быть виден в гите, то я захожу в project и создаю отдельный файл properties,
* в котором создаю переменную, которая будет хранить сам токен.
* 2. Этот файл мне необходимо добавить в файл gitignore двумя способами
*
* 3. Уже в самом Android я захожу в Gradle и совершаю серию действий (смотри Gradle app)
* 4. После того как я настроил в Gradle глобальную переменную (токен) я прихожу сюда.
* 5. Для начала мне необходимо указать заголовки HTTP запроса
* */

interface ServerApi {
    @Headers(
        "Content-Type: application/json", //это заголовок, который указывает на тип контента (Спасибо Кэп)
        //их вообще много разных, но конкретно этот "application/json" - используется когда мы не знаем как будем использовать информацию,
        // но нам просто нужно ее извлечь из сервера в JSON формате.

        "apikey: ${BuildConfig.ACCESS_TOKEN}", //наверное здесь просто указываем токен для подключения

        "Authorization: Bearer ${BuildConfig.ACCESS_TOKEN}" /* это способ аутентификации
         насколько я понял, то после ввода данных (почты и пароля), если они правильные и такой пользователь есть,
         то этот Bearer генерирует то ли новый токен, то ли как-то допускает к базовому, который мы в properties записывали.. хз короче
         */
    )
    @POST("/auth/v1/signup") //указываю HTTP запрос POST, который используется когда нужно что-то отправить на сервер.
    //с этим запросом используется аннотация ретрофита Body, которая переводит ответ из сервера в JSON формат
    //создаем параллельно выполняемую функцию, которая будет отдавать нам ответ обернутый в Call
    // продолжение следует (от сюда сразу переходу в Impl)
   suspend fun registerUser(@Body user: User): Call<UserResponce>
}

data class User(
    val email: String,
    val password: String
)

data class UserResponce(
    val id: String?,
    val email: String?
)