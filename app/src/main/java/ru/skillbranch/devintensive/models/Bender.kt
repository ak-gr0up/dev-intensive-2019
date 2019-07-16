package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    var wrongAnswers: Int = when(status){Status.NORMAL -> 0; Status.WARNING -> 1; Status.DANGER -> 2; Status.CRITICAL -> 3}

    fun validation(answer: String): String{
        if (question == Question.NAME){
            return when(answer[0].toUpperCase() == answer[0]){
                true -> ""
                false -> "Имя должно начинаться с заглавной буквы"
            }
        }
        else if (question == Question.PROFESSION){
            return when(answer[0].toLowerCase() == answer[0]){
                true -> ""
                false -> "Профессия должна начинаться со строчной буквы"
            }
        }
        else if (question == Question.MATERIAL){
            return when('0' in answer || '1' in answer || '2' in answer || '3' in answer || '4' in answer ||
                    '5' in answer || '6' in answer || '7' in answer || '8' in answer || '9' in answer){
                true -> "Материал не должен содержать цифр"
                false -> ""
            }

        }
        else if (question == Question.BDAY || question == Question.SERIAL){
            var allLetters = true
            for (i in (0..answer.length - 1)){
                if (answer[i] != '0' && answer[i] != '1' && answer[i] != '2' && answer[i] != '3' && answer[i] != '4' &&
                    answer[i] != '5' && answer[i] != '6' && answer[i] != '7' && answer[i] != '8' && answer[i] != '9'){
                    allLetters = false
                    break
                }
            }
            return when(allLetters){true -> "" false -> when(question == Question.BDAY){true ->"Год моего рождения должен содержать только цифры" false -> "Серийный номер содержит только цифры, и их 7"}}
        }
        return ""
    }
    fun listenAnswer(answerToUp: String): Pair<String, Triple<Int, Int, Int>>{
        val checkValidation = validation(answerToUp)
        if (checkValidation == ""){
            val answer = answerToUp.toLowerCase()
            if (question.answers.contains(answer)){
                question = question.nextQuestion()
                return "Отлично - ты справился\n${question.question}" to status.color
            }
            else {
                if (wrongAnswers == 3){
                    wrongAnswers = 0
                    status =  Status.NORMAL
                    question = Question.NAME
                    return "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                }
                if (question != Question.IDLE) {
                    wrongAnswers ++
                    status = status.nextStatus()
                    return "Это неправильный ответ\n${question.question}" to status.color
                }
                else{
                    return "На этом все, вопросов больше нет" to status.color
                }

                }
            }

        else{
            return "${checkValidation}\n${question.question}" to status.color
        }

    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status = values()[(ordinal + 1) % Status.values().size]
    }
    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")),
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")),
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")),
        BDAY("Когда меня создали?", listOf("2993")),
        SERIAL("Мой серийный номер?", listOf("2716057")),
        IDLE("На этом все, вопросов больше нет", listOf());

        fun nextQuestion() : Question{
            if (ordinal + 1 == 7){
                return IDLE
            }
            else{
                return values()[(ordinal + 1)]
            }

        }

    }
}