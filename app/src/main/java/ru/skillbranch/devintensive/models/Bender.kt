package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
    private var incorrectAnswersCount = 0

    fun askQuestion(): String = question.question

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        validateAnswer()

        return if (question.answer.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - это правильный ответ!\n${question.question}" to status.color
        } else {
            incorrectAnswersCount++
            if (incorrectAnswersCount == 3) {
                resetState()
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                status = status.nextStatus()
                "Это не правильный ответ\n${question.question}" to status.color
            }
        }
    }

    private fun validateAnswer() {
        
    }

    private fun resetState() {
        incorrectAnswersCount = 0
        status = Status.NORMAL
        question = Question.NAME
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status = values().getOrElse(ordinal + 1) { NORMAL }
    }

    enum class Question(val question: String, val answer: List<String>) {

        NAME("Как меня зовут?", listOf("бендер", "bender")),
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")),
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")),
        BDAY("Когда меня создали?", listOf("2993")),
        SERIAL("Мой серийный номер?", listOf("2716057")),
        IDLE("На этом все, вопросов больше нет", emptyList());

        fun nextQuestion(): Question = values().getOrElse(ordinal + 1) { IDLE }

    }

}