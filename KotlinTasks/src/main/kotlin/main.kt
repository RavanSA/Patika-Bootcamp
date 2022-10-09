class TaskFirstWeekBootcamp {

    fun codelandUsernameValidation(str: String): String {

        val rgx: Regex = """[A-Za-z0-9\\_]+""".toRegex()
        val charArr: CharArray = str.toCharArray()
        val lastElement: String = charArr[charArr.size - 1].toString()

        val firstCase: Boolean = (str.length in 4..25)
        val secondCase: Boolean = charArr[0] in 'a'..'z' || charArr[0] in 'A'..'Z'
        val thirdCase: Boolean = str.matches(rgx)
        val fourthCase: Boolean = lastElement != "_"

        return (firstCase && secondCase && thirdCase && fourthCase).toString()
    }

    fun aVeryBigSum(arr: Array<Long>): Long {
        var sumOfArray: Long = 0
        arr.forEach { sumOfArray += it }
        return sumOfArray
    }

    fun firstFactorial(
        solutionType: String = "recursive",
        num: Int
    ): Int {
        var result = 1

        when(solutionType) {
            "recursive" -> {
                if (num == 0 || num == 1) return result
                result = num * firstFactorial(num = num - 1)
            }
            "iterative" -> {
                if (num == 0 || num == 1) return result
                for(i in 1..num ) {
                    result *= i
                }
            }
        }

        return result
    }

    fun questionsMarks(str: String): String {
        var result = "true"
        val charArr = str.toCharArray()
        val charArrSize = charArr.size - 1
        var count = 0
        for (i in 0..charArrSize) {
            for (j in 0..charArrSize) {
                if (charArr[i].isDigit() && charArr[j].isDigit()) {
                    val sum = Character.getNumericValue(charArr[i]) + Character.getNumericValue(charArr[j])
                    if (sum == 10) {
                        result = "true"
                        if (i > j) {
                            for (a in j..i) {
                                if(charArr[a] =='?') {
                                    count+=1
                                }
                            }
                            result = if(count % 3 != 0) {
                                "false"
                            } else {
                                "true"
                            }
                        } else if (i < j) {
                            for( k in i..j){
                             if(charArr[k] == '?') {
                                 count+=1
                             }
                         }
                            result = if(count % 3 !=0) {
                                "false"
                            } else {
                                "true"
                            }
                            count = 0
                        }
                        else if(charArrSize == i && charArrSize == j) {
                            if(count % 3 != 0 && count != 0){
                                result = "false"
                            }
                        }
                    } else {
                        if(i != j ){
                            result = "false"
                        }
                    }
                } else {
                    if(kotlin.math.abs(i - j) > 3) {
                        result = "false"
                    }
                }
            }
        }

        return result
    }

}

fun main(args: Array<String>) {
    val str: String = "u__hello_world123"
    val firstTask = TaskFirstWeekBootcamp().codelandUsernameValidation(str)

    val arr: Array<Long> = arrayOf(15515, 98521, 6148994415212, 84415218, 841156415)
    val secondTask = TaskFirstWeekBootcamp().aVeryBigSum(arr)
    val num: Int = 12
    val thirdTask = TaskFirstWeekBootcamp().firstFactorial(num = num)


    val strQuestionMark = "acc?7??sss?3rr1??????5"
    val fourthTask = TaskFirstWeekBootcamp().questionsMarks(strQuestionMark)
    println(fourthTask)

}