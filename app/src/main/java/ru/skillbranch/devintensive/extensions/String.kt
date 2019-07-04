package ru.skillbranch.devintensive.extensions

fun String.truncate(cut: Int = 16): String?{
    if (cut >= this.dropLast((this.length - cut)).trimEnd(' ').length){
        return this.dropLast((this.length - cut)).trimEnd(' ')
    }
    else {
        return "${this.dropLast((this.length - cut)).trimEnd(' ')}..."
    }
}

fun String.stripHtml(): String?{ // &amp;|&lt;|&gt;|&#39;|&quot; - это escape последовательности для html символов(ИСПРАВИТЬ!)
    var space = false
    var escape = false
    var tags = false
    var result = ""
    var miss = 0
    for (i in this){
        if (i == ' '){
            if (!space)
                if (!tags && !escape) {
                    result += " "
                }
            space = true
        }
        else if (i == '&') {
            escape = true
            continue
        }
        else if (i == '<') {
            tags = true
            continue
        }
        else if (i == '>' && tags) {
            tags = false
            continue
        }
        else if (i == ';' && escape) {
            escape = false
            continue
        }
        else {
            space = false
        }
        if (!tags && !escape && !space)
            result += i
    }
    var resultWithoutSpaces: String = ""
    for (i in 0..(result.length - 1)){
        if (miss != 0){
            miss --
            continue
        }
        if(i != 0 && result[i] == ' ' && result[i-1] == ' ' || result[i] == '\n') {
            continue
        }
        if (i < result.length - 5) {
            val escCheck = result[i].toString() + result[i+1].toString() + result[i+2].toString() + result[i+3].toString() + result[i+4].toString()
            if (escCheck == "&amp;" || escCheck == "&#39;"){
                miss = escCheck.length
            }
        }
        if (i < result.length - 4) {
            val escCheck = result[i].toString() + result[i+1].toString() + result[i+2].toString() + result[i+3].toString()
            if (escCheck == "&lt;" || escCheck == "&gt;"){
                miss = escCheck.length
            }
        }
        if (i < result.length - 6) {
            val escCheck = result[i].toString() + result[i+1].toString() + result[i+2].toString() + result[i+3].toString() +
            result[i+4].toString() + result[i+5].toString()
            if (escCheck == "&quot;"){
                miss = escCheck.length
            }
        }

        resultWithoutSpaces += result[i]
    }
    return resultWithoutSpaces

}
