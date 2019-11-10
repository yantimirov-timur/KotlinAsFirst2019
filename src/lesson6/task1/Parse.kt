@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import kotlin.math.roundToInt

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val list = str.split(" ").toMutableList()

    val monthList = listOf<Pair<String, String>>(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
    )

    if (list.size != 3 || monthList.all { it.first != list[1] } || list[0].toInt() > 31)
        return emptyList<String>().joinToString()
    for ((key, value) in monthList) {
        if (key == list[1]) {
            list[1] = value
            break
        }
    }
    val day = daysInMonth(list[1].toInt(), list[2].toInt())
    if (list[0].toInt() < 10 && list[0].first() != '0') {
        list[0] = "0" + list[0]
    }
    if (day < list[0].toInt())
        return emptyList<String>().joinToString()

    return list.joinToString(separator = ".")
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val list = digital.split(".").toMutableList()
    val monthList = listOf<Pair<String, String>>(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
    )
    if (list.size != 3 || monthList.all { it.second != list[1] } || list[0].toInt() > 31)
        return emptyList<String>().joinToString()
    val day = daysInMonth(list[1].toInt(), list[2].toInt())
    for ((key, value) in monthList) {
        list[0].toInt()
        if (value == list[1]) {
            list[1] = key
            break
        }
    }
    if (list[0].toInt() < 10) {
        list[0] = list[0].toInt().toString()
    }
    if (day < list[0].toInt())
        return emptyList<String>().joinToString()
    return list.joinToString(separator = " ")
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val matchedResults = Regex("""[+?\d]+""").findAll(phone)
    val bracket = Regex("""\([^)]*\)""").find(phone)?.value
    val anySymbols = Regex("""[^\d([+]\s[-])]+""").find(phone)?.value
    var res = ""
    for (i in matchedResults) {
        res += i.value
    }
    if (bracket == "()" || anySymbols != null)
        return ""
    return res
}


/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var res = 0
    val matchedResults = Regex("""[\d]+""").findAll(jumps)
    val anySymbols = Regex("""[^\d([%]\s[-]]+""").find(jumps)?.value
    for (results in matchedResults) {
        if (results.value.toInt() > res)
            res = results.value.toInt()
    }
    if (res == 0 || anySymbols != null)
        return -1
    return res
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var res = 0
    val matchedResults = Regex("""[\d]+[\s][%]*[+]""").findAll(jumps)
    val anySymbols = Regex("""[^\d([%]\s[-][+]]+""").find(jumps)?.value
    var correctDigits = ""
    for (result in matchedResults) {
        correctDigits += result.value
    }
    val digits = Regex("""[\d]+""").findAll(correctDigits)
    for (result in digits) {
        if (res < result.value.toInt())
            res = result.value.toInt()

    }
    if (anySymbols != null)
        return -1
    return res
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val list = expression.split(" ")
    try {
        var res = list[0].toInt()
        var x = 1
        var y = 2
        require((!Regex("""([-+]\d+|\d+[-+])""").containsMatchIn(list.toString()) || expression.isEmpty()))
        while (x != list.size) {
            when (list[x]) {
                "+" -> res += list[y].toInt()
                "-" -> res -= list[y].toInt()
            }
            x += 2
            y += 2
        }
        return res

    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }

}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Яблоко упало на ветку с ветки оно упало на на землю" => результат 40 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int =TODO()



/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val listOfPair = mutableListOf<Pair<String, Double>>()
    val name = Regex("""[а-яА-ЯёЁa-zA-Z]+""").findAll(description)
    val prices = Regex(""" [\d.]+""").findAll(description)
    val anySymbols = Regex("""[^а-яА-ЯёЁa-zA-Z\d.\s]+""").findAll(description)
    val listOfNames = mutableListOf<String>()
    val listOfPrices = mutableListOf<Double>()
    val listOfSymbols = mutableListOf<String>()


    for (i in anySymbols) {
        listOfSymbols.add(i.value)
    }
    for (i in prices) {
        listOfPrices.add(i.value.toDouble())
    }
    for (i in name) {
        listOfNames.add(i.value)
    }

    if (listOfNames.isEmpty() && listOfSymbols.isEmpty()) return ""

    for (i in 0 until listOfPrices.size) {
        if (listOfPrices[i].roundToInt() == 0.0.roundToInt())
            listOfPair.add((listOfSymbols[i] + listOfNames[i]) to listOfPrices[i])
        else
            listOfPair.add(listOfNames[i] to listOfPrices[i])
    }

    var max = listOfPair.first()
    for (i in 0 until listOfPair.size) {
        if (listOfPair[i].second >= max.second)
            max = listOfPair[i]
    }

    return max.first
}


/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    var res = 0
    val difElement = Regex("""IX|XL|XC|CD|CM|IV""").findAll(roman)
    val simpleElement = Regex("""IX|XL|XC|CD|CM|IV""").split(roman).toString().toCharArray()
    val exception = Regex("""[^XCIVDLM]""").findAll(roman).toList()
    val list = mutableListOf<String>()
    if (exception.isNotEmpty() || roman.isEmpty())
        return -1
    for (i in difElement) {
        list.add(i.value)
    }
    for (i in 0 until list.size) {
        when (list[i]) {
            "IV" -> res += 4
            "IX" -> res += 9
            "XL" -> res += 40
            "XC" -> res += 90
            "CD" -> res += 400
            "CM" -> res += 900
        }
    }
    for (element in simpleElement) {
        when (element) {
            'I' -> res += 1
            'V' -> res += 5
            'X' -> res += 10
            'L' -> res += 50
            'C' -> res += 100
            'D' -> res += 500
            'M' -> res += 1000

        }
    }
    return res
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
