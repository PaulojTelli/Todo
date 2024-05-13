package com.example.scrollablelist.data

import com.example.scrollablelist.R
import com.example.scrollablelist.models.Todo

fun createTodosList(): List<Todo> {
    val pictures = listOf(
        R.drawable.baseline_face_24,
        R.drawable.baseline_face_2_24,
        R.drawable.baseline_face_3_24,
        R.drawable.baseline_face_4_24,
        R.drawable.baseline_face_5_24,
        R.drawable.baseline_face_6_24
    )

    val characters = listOf(
        "Yoda" to "May the Force be with you.",
        "Luke Skywalker" to "I am a Jedi, like my father before me.",
        "Darth Vader" to "I am your father.",
        "Princess Leia" to "Help me, Obi-Wan Kenobi. You're my only hope.",
        "Han Solo" to "I've got a bad feeling about this.",
        "Harry Potter" to "I solemnly swear that I am up to no good.",
        "Hermione Granger" to "It's leviOsa, not levioSA!",
        "Ron Weasley" to "Bloody hell!",
        "Albus Dumbledore" to "Happiness can be found, even in the darkest of times.",
        "Severus Snape" to "Always.",
        "Tony Stark" to "I am Iron Man.",
        "Thor" to "I'm not a queen or a monster. I'm the Goddess of Death.",
        "Captain America" to "I can do this all day.",
        "Black Widow" to "I'm always picking up after you boys.",
        "Spider-Man" to "With great power comes great responsibility.",
        "Batman" to "I'm Batman.",
        "Superman" to "Truth, justice, and the American way.",
        "Wonder Woman" to "I believe in love.",
        "The Joker" to "Why so serious?",
        "Sherlock Holmes" to "Elementary, my dear Watson.",
        "Gandalf" to "A wizard is never late, nor is he early.",
        "Frodo Baggins" to "I wish the ring had never come to me.",
        "Aragorn" to "For Frodo.",
        "Legolas" to "They're taking the hobbits to Isengard!",
        "Gimli" to "Nobody tosses a dwarf!",
        "Saruman" to "Against the power of Mordor, there can be no victory.",
        "Samwise Gamgee" to "I can't carry it for you, but I can carry you!",
        "Gollum" to "My precious.",
        "Bilbo Baggins" to "I'm going on an adventure!",
        "Elrond" to "I was there the day the strength of Men failed.",
        "Galadriel" to "Even the smallest person can change the course of the future.",
        "Sauron" to "You cannot hide. I see you.",
        "Thranduil" to "Do not speak to me of dragon fire. I know its wrath and ruin.",
        "Boromir" to "One does not simply walk into Mordor.",
        "Faramir" to "I do not seek to command the loyalty that my brother won.",
        "Eowyn" to "I am no man.",
        "King Theoden" to "So it begins.",
        "Aragorn (as King)" to "You bow to no one.",
        "Tauriel" to "If this is love, I do not want it.",
        "Kili" to "I will see you again.",
        "Gandalf (to Bilbo)" to "I am looking for someone to share in an adventure.",
        "Lurtz" to "Find the Halflings!",
        "Gríma Wormtongue" to "He must be dead by now.",
        "Denethor" to "You think you are wise, Mithrandir.",
        "Haldir" to "The world is changing.",
        "Éomer" to "Riders of Rohan! Oaths you have taken, now fulfill them all, to lord and land!"
    )

    val contacts = characters.mapIndexed { index, (name, status) ->
        Todo(picture = pictures[index % pictures.size], name = name, status = status)
    }

    return contacts
}