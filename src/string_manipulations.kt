import java.util.*

// убирает лишние пробелы из строки для дальнейшей обработки
fun converter(): String {
    val scanner = Scanner(System.`in`)
    var str: String = scanner.nextLine().trim();
    while(str.contains("  ")) {
        str = str.replace("  ", " ")
    }
    return str
} // вырезаем первое слово из общей строки
fun getFirstWord(str: String): String {
    val index = str.indexOf(' ')
    if(index != -1){
        return str.substring(0, index)
    }
    return str
} // обрезка строки без первого слова для дальнейшей работы
fun getArguments(str: String): String {
    return str.substring(str.indexOf(' ') + 1, str.length)
} // проверка введённых данных на корректность по типу данных
fun isArgumentsCorrect(str: String): Boolean {
    val piecesOfCat = getArguments(str).split(" ").toTypedArray()
    if(piecesOfCat.size == 3) {
        if(isInt(piecesOfCat[1]) && isFloat(piecesOfCat[2]))
        return true
    }
    return false
} // доп. функция для проверки типа данных
fun isInt(pieceOfCat: String): Boolean {
    val filterPiece = pieceOfCat.filter { it.isDigit() }
    if(filterPiece == pieceOfCat) return true
    return false
} // доп. функция для проверки типа данных
// TODO оптимизировать
fun isFloat(pieceOfCat: String): Boolean {
    val nums = pieceOfCat.split('.')
    if(nums.size <= 2) {
        for(element in nums) {
            val filterPiece = element.filter { it.isDigit() }
            if(filterPiece != element || element.isBlank()) return false
        }
    } else return false
    return true
}

// берем индекс из таблицы для хранения пары
fun getBucketIndex(key: String): Int {
    return kotlin.math.abs(key.hashCode() % numOfBuckets)
}// поиск пары по первому элементу
fun search(index: Int, key: String): Int {
    for(element in CatHashTable[index]) {
        if(element.first == key) {
            return CatHashTable[index].indexOf(element) // есть такой элемент, ретурн его индекс
        }
    }
    return -1 // нет такого элемента
}



























