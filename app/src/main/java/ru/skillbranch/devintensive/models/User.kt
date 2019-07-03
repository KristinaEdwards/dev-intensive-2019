package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {


    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe $id")

    init {

        val nameMessage =
            if (lastName == "Doe")
                "His name is $firstName $lastName"
            else
                "And his name is $firstName $lastName!!!"

        val message = "It's Alive!!!\n$nameMessage\n"
        println(message)
    }

    class Builder {
        private var id: String = (++lastId).toString()
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = Date()
        private var isOnline: Boolean = false

        fun id(id: String) = apply { this.id = id }

        fun firstName(s: String?) = apply { this.firstName = s }

        fun lastName(s: String?) = apply { this.lastName = s }

        fun avatar(s: String?) = apply { this.avatar = s }

        fun rating(n: Int) = apply { this.rating = n }

        fun respect(n: Int) = apply { this.respect = n }

        fun lastVisit(d: Date?) = apply { this.lastVisit = d }

        fun isOnline(b: Boolean) = apply { this.isOnline = b }

        fun build() = User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar,
            rating = rating,
            respect = respect,
            lastVisit = lastVisit,
            isOnline = isOnline
        )
    }

    companion object Factory {

        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = lastId.toString(), firstName = firstName, lastName = lastName)
        }
    }

}
