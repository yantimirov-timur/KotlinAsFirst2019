@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }
    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val reverse = mutableMapOf<Int, List<String>>()
    for ((name, grade) in grades) {
        reverse[grade] = reverse.getOrDefault(grade, listOf()) + name
    }
    return reverse
}


/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((x1, x2) in a) {
        if (b[x1] != x2) return false
    }
    return true
}


/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    val equalstoremove = mutableListOf<String>()
    for ((x1, x2) in a) {
        if (b[x1] == x2) {
            equalstoremove.add(x1)
        }
    }
    for (x1 in equalstoremove) {
        a.remove(x1)
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val result = mutableListOf<String>()
    a.toMutableSet()
    b.toMutableSet()
    return result + a.intersect(b)
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для *повторяющихся* ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val res = mutableMapOf<String, String>()
    val list = mutableListOf<String>()
    for ((name, number) in mapB) {
        if (name in mapA) {
            if (mapA[name] == mapB[name]) {
                res[name] = number
            } else {
                mapA[name]?.let { list.add(it) }
                mapB[name]?.let { list.add(it) }
                res[name] = list.joinToString()
            }
        } else
            res[name] = number
    }
    for ((name, number) in mapA) {
        if (name !in mapB) {
            res[name] = number
        }
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "NFLX" to 40.0, "MSFT" to 200.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val meanPrice = mutableListOf<Double>()
    val map = mutableMapOf<String, Double>()
    if (stockPrices.isEmpty()) return emptyMap()
    var y = 0
    var name = stockPrices[y]
    var x = 0
    while (x != stockPrices.size) {
        for ((key, value) in stockPrices) {
            if (name.first == key) {
                meanPrice.add(value)
                map.put(name.first, lesson4.task1.mean(meanPrice))
            }
            x += 1
        }
        x = 1
        y += 1
        meanPrice.clear()
        if (stockPrices.all { it.first in map })
            break
        else
            name = stockPrices[y]
    }
    return map
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть **название товара-заданного типа с минимальной стоимостью**
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Любятово" to ("печенье" to 20.0), "Орео" to ("торт" to 100.0)),
 *     "печенье"
 *   ) -> "Любятово"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var name = ""
    if (stuff.all { it.value.first != kind }) return null
    val map = stuff.values.groupBy { it.first == kind }
    val list = mutableListOf<Double>()
    for ((key, value) in map) {
        value.all { key && list.addAll(listOf(it.second)) }
    }
    for ((key, value) in stuff) {
        if (list.min() != null) {
            if (list.min() == value.second && value.first == kind) {
                name = key
            }
        }
    }
    return name
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    val list1 = word.toCharArray()
    for (element in list1) {
        if (chars.any { it.toUpperCase() == element.toUpperCase() } && word.length == 1)
            return true
    }
    return if (list1.all { it in chars }) true
    else list1.any { list1.map { it.toUpperCase() } == chars.map { it.toUpperCase() } }
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val res = mutableMapOf<String, Int>()
    var count = 0
    for (i in 1 until list.size) {
        count = list.count { it == list[i] }
        if (count != 1)
            res[list[i]] = count
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    if (words.isEmpty()) return false
    var word = words.first().toCharArray()
    var x = 1
    var y = 0
    while (x != words.size) {
        for (i in x until words.size) {
            (words[i]).toCharArray()
            if (words[i].all { it in word } && words[i].length == word.size)
                return true
        }
        x += 1
        y += 1
        word = words[y].toCharArray()
    }

    return false
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Mikhail" to setOf("Sveta")
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *
 *
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> = TODO()

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    var res1 = 0
    var res2 = 0
    var x = 0
    var y = 0
    var count = 0
    while (count != list.size) {
        for (i in x until list.size) {
            if (list[y] + list[x] == number && x != y) {
                res1 = y
                res2 = x
            }
            x += 1
        }
        if (res1 == 0 && res2 == 0) {
            y += 1
            x = 0
            count++
            continue
        } else
            return res1 to res2
    }
    if (list.isEmpty()) return -1 to -1
    if (res1 == 0 && res2 == 0) return -1 to -1
    return res1 to res2
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, cap: Int): Set<String> {
    val res = mutableSetOf<String>()
    var x = 0
    var cap = cap
    var n = treasures.keys.count()
    val table = Array(n) { Array(n) { 0 } }
    var mass = 0
    var price = 0
    var currentItems = 0
    var weight = 0
    val priceList = mutableListOf<Pair<Int, Int>>()
    for ((_, value) in treasures) {
        priceList.add(value.first to value.second)
    }
    priceList.sortBy { it.second }

    for (i in 0 until priceList.size) {
        if (weight + priceList[i].first <= cap) {
            weight += priceList[i].first
            price += priceList[i].second
        }
    }
    for ((key, value) in treasures) {
        if (value.first == weight)
            res += key
    }
    return res
}
