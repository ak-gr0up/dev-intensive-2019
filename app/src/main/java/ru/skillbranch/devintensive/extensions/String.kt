package ru.skillbranch.devintensive.extensions

fun String.truncate(cut: Int = 16): String? = "${this.dropLast((this.length - cut))}..."

fun String.stripHtml(): String?{
    var space = false
    var escape = false
    var tags = false
    var result = ""
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
        if(i != 0 && result[i] == ' ' && result[i-1] == ' ' || result[i] == '\n') {
            continue
        }
        resultWithoutSpaces += result[i]
    }
    return resultWithoutSpaces

}
