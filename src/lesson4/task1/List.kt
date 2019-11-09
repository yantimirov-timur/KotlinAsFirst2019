@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.map { it * it }.sum())

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.isEmpty()) 0.0
    else (list.sum() / list.size)
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val arithmetic = mean(list)
    for (i in 0 until list.size) {
        list[i] = list[i] - arithmetic
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var mult = 0
    for (i in a.indices) {
        mult += a[i] * b[i]
    }
    return mult
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0
    for (i in p.indices) {
        px += p[i] * x.toDouble().pow(i).toInt()
    }
    return px
}


/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] = list[i - 1] + list[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var del = 2
    var numb = n
    while (numb != 1) {
        if (numb % del == 0) {
            numb /= del
            list.add(del)
        } else {
            del += 1
        }
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val list = mutableListOf<Int>()
    if (number == 0)
        list.add(number)
    while (number >= 1) {
        val ost: Int = number % base
        number /= base
        list.add(ost)
    }
    return list.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = polynom(digits.reversed(), base)


/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var sumList = String()
    var number = n
    val listLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray()
    var mod: Int
    do {
        mod = number % base
        number /= base
        if (mod >= 10) {
            sumList += listLetters[mod - 10]
        } else if (mod < 10) {
            sumList += mod
        }
    } while (number >= 1)

    return sumList.reversed()
}


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var number = n
    var mod1 = 0
    var thousand = 0
    val sumListThousands = mutableListOf<String>()
    val sumListHundred = mutableListOf<String>()
    val listWordsUnits =
        listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val listWordsThousand =
        listOf("одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val listWordsDecades = listOf(
        "десять",
        "двадцать",
        "тридцать",
        "сорок",
        "пятьдесят",
        "шестьдесят",
        "семьдесят",
        "восемьдесят",
        "девяносто"
    )
    val listWordsHundred = listOf(
        "сто",
        "двести",
        "триста",
        "четыреста",
        "пятьсот",
        "шестьсот",
        "семьсот",
        "восемьсот",
        "девятьсот"
    )
    val listWordsUnits1 = listOf(
        "одиннадцать",
        "двенадцать",
        "тринадцать",
        "четырнадцать",
        "пятнадцать",
        "шестнадцать",
        "семнадцать",
        "восемнадцать",
        "девятнадцать"
    )
    val listDigits = listOf(100, 200, 300, 400, 500, 600, 700, 800, 900)
    var mod: Int
    while (number > 0) {
        //Условие для 1значного числа
        if (number.toString().length == 1 && number !in 11..19) {
            mod1 = number % 10
            sumListHundred.add(listWordsUnits[mod1 - 1])
            if (number.toString().length in 4..6) {
                (sumListThousands + sumListHundred).joinToString(separator = " ")

            } else sumListHundred.joinToString(separator = " ")
            number /= 10

        } else if (number.toString().length == 1 && number in 11..19) {
            sumListHundred.add(listWordsUnits1[mod1 - 11])
            return sumListHundred.joinToString(separator = " ")

        }
        //Условие для 2значного числа
        else if (number.toString().length == 2 && number !in 11..19) {
            mod1 = number / 10
            sumListHundred.add(listWordsDecades[mod1 - 1])
            number %= 10
        } else if (number.toString().length == 2 && number in 11..19) {
            sumListHundred.add(listWordsUnits1[number - 11])
            number /= 100
        }
        //Условие для 3 значного числа
        else if (number.toString().length == 3) {
            mod1 = number / 100
            sumListHundred.add(listWordsHundred[mod1 - 1])
            number %= 100
        }

        //Услвие при 1000+ числе
        else if (number.toString().length in 4..6) {
            thousand = number / 1000
        }
        //1-19 тысяч
        while (thousand > 0) {
            if (thousand.toString().length == 1 && thousand !in 11..19) {
                thousand %= 10
                when {
                    thousand in 2..4 -> sumListThousands.add(listWordsThousand[thousand - 1] + " тысячи")
                    (thousand == 1) -> sumListThousands.add(listWordsThousand[thousand - 1] + " тысяча")
                    thousand in 5..10 -> sumListThousands.add(listWordsUnits[thousand - 1] + " тысяч")
                }
                thousand /= 10
            } else if (mod1.toString().length == 1 && thousand in 11..19) {
                sumListThousands.add(listWordsUnits1[thousand - 11] + " тысяч")
                thousand /= 100
            }
            //для 20-99 тысяч

            else if (thousand.toString().length == 2 && thousand !in 11..19) {
                mod = thousand / 10
                thousand %= 10
                if (thousand % 10 == 0) {
                    sumListThousands.add(listWordsDecades[mod - 1] + " тысяч")
                } else {
                    sumListThousands.add(listWordsDecades[mod - 1])

                }

            } else if (thousand.toString().length == 2 && thousand in 11..19) {
                sumListThousands.add(listWordsUnits1[thousand - 11])
                //-thousand %= 10
            }
            //для 100+ тысяч
            else if (thousand.toString().length == 3) {
                mod = thousand / 100
                if (thousand in listDigits) {
                    sumListThousands.add(listWordsHundred[mod - 1] + " тысяч")
                } else {
                    sumListThousands.add(listWordsHundred[mod - 1])
                }
                thousand %= 100
            }
            number %= 1000
        }
    }
    return (sumListThousands + sumListHundred).joinToString(separator = " ")
}