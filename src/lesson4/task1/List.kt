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
fun abs(v: List<Double>): Double = sqrt(v.map { it * it }.toDoubleArray().sum())

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.isEmpty()) 0.0
    else (list.toDoubleArray().sum() / list.size)
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
    var st = 1
    for (i in 1 until p.size) {
        px += p[i] * x.toDouble().pow(st).toInt()
        st += 1
    }
    return if (p.isEmpty()) px
    else
        px + p.first()
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
        } else if (numb % del != 0) {
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
fun factorizeToString(n: Int): String {//оптимизировать(?)
    val list = mutableListOf<Int>()
    var del = 2
    var numb = n
    while (numb != 1) {
        if (numb % del == 0) {
            numb /= del
            list.add(del)
        } else if (numb % del != 0) {
            del += 1
        }
    }
    return list.joinToString(separator = "*")
}

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
    var ost = 0
    if (number == 0)
        list.add(number)
    while (number >= 1) {
        ost = number % base
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
fun decimal(digits: List<Int>, base: Int): Int {
    var chislo = 0
    var st = digits.size - 1
    for (element in digits) {
        chislo += element * base.toDouble().pow(st).toInt()
        st -= 1
    }
    return chislo
}

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
    var sumlist = String()
    var number = n
    val list = mutableListOf<Int>()//пустой лист с цифрами
    val listletters = "abcdefghijklmnopqrstuvwxyz".toCharArray()
    val emptylist = mutableListOf<Char>()//Пустой лист с буквами
    var mod = 0//остаток
    if (number == 0) {
        list.add(number)
        sumlist += list.joinToString()
    }
    while (number >= 1) {
        mod = number % base
        number /= base
        if (mod >= 10) {
            emptylist.add(listletters[mod - 10])
            sumlist += emptylist.joinToString(separator = "")
        } else if (mod < 10) {
            list.add(mod)
            sumlist += list.joinToString(separator = "")
        }
        list.clear()
        emptylist.clear()
    }
    return sumlist.reversed()
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
    var thausands = 0
    val sumlist1 = mutableListOf<String>()//Список для тысяч
    val sumlist = mutableListOf<String>()//Список для сотен
    val listwords1 =
        listOf<String>("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять") //буквы от 1 до 9
    val listwordsthousand =
        listOf<String>("одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")//для тысяч
    val listwords2 = listOf<String>(//Десятки
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
    val listwords3 = listOf<String>(//Сотни
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
    val listwords2_1 = listOf<String>(//Условие при 11..19
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
    val listdigits = listOf<Int>(100, 200, 300, 400, 500, 600, 700, 800, 900)//сотни без десятков
    var mod = 0
    while (number > 0) {
        if (number.toString().length == 1 && number !in 11..19) {
            mod1 = number % 10
            sumlist.add(listwords1[mod1 - 1])
            when (number.toString().length) {
                4 -> (sumlist1 + sumlist).joinToString(separator = " ")
                5 -> (sumlist1 + sumlist).joinToString(separator = " ")
                6 -> (sumlist1 + sumlist).joinToString(separator = " ")
                else -> sumlist.joinToString(separator = " ")
            }
            number /= 10

        } else if (number.toString().length == 1 && number in 11..19) {
            sumlist.add(listwords2_1[mod1 - 11])
            return sumlist.joinToString(separator = " ")

        }

        //Условие для 2значного числа
        else if (number.toString().length == 2 && number !in 11..19) {
            mod1 = number / 10
            sumlist.add(listwords2[mod1 - 1])
            number %= 10
        } else if (number.toString().length == 2 && number in 11..19) {
            sumlist.add(listwords2_1[number - 11])
            number /= 100
        }

        //Условие для 3 значного числа
        else if (number.toString().length == 3) {
            mod1 = number / 100
            sumlist.add(listwords3[mod1 - 1])
            number %= 100
        }

        //Услвие при 1000+ числе
        else if (number.toString().length == 4 || number.toString().length == 5 || number.toString().length == 6) {
            thausands = number / 1000

            while (thausands > 0) {
                //1-19 тысяч
                if (thausands.toString().length == 1 && thausands !in 11..19) {
                    thausands %= 10
                    if (thausands in 2..4)
                        sumlist1.add(listwordsthousand[thausands - 1] + " тысячи")
                    else if (thausands == 1)
                        sumlist1.add(listwordsthousand[thausands - 1] + " тысяча")
                    else if (thausands in 5..10)
                        sumlist1.add(listwords1[thausands - 1] + " тысяч")
                    thausands /= 10
                } else if (mod1.toString().length == 1 && thausands in 11..19) {
                    sumlist1.add(listwords2_1[thausands - 11] + " тысяч")
                    thausands /= 100
                }
                //для 20-99 тысяч

                else if (thausands.toString().length == 2 && thausands !in 11..19) {
                    if (thausands % 10 == 0) {
                        mod = thausands / 10
                        sumlist1.add(listwords2[mod - 1] + " тысяч")
                        thausands %= 10
                    } else {
                        mod = thausands / 10
                        sumlist1.add(listwords2[mod - 1])
                        thausands %= 10
                    }

                } else if (thausands.toString().length == 2 && thausands in 11..19) {
                    sumlist1.add(listwords2_1[thausands - 11])
                    thausands %= 10
                }
                //для 100+ тысяч
                else if (thausands.toString().length == 3) {
                    mod = thausands / 100
                    thausands %= if (thausands in listdigits) {
                        sumlist1.add(listwords3[mod - 1] + " тысяч")
                        100
                    } else {
                        sumlist1.add(listwords3[mod - 1])
                        100
                    }
                }
            }
            number %= 1000
        }
    }
    if (sumlist.isEmpty()) return sumlist1.joinToString(separator = " ")
    return (sumlist1 + sumlist).joinToString(separator = " ")
}