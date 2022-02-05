import java.util.*
// дата класс для составления пар
data class Cat(val colour: String, val age: Int, val weight: Float)
// хеш-таблица
var CatHashTable = Array<LinkedList<Pair<String, Cat>>> (10) {_ -> LinkedList<Pair<String, Cat>>() }
// кол-во индексов в хеш-таблице, пока что 10 для более простого тестирования
val numOfBuckets = 10

// функция для добавления элемента
fun create(catInfo: String) {
    // проверка данных на корректность
    if(isArgumentsCorrect(catInfo)) {
        // ключ - имя кота
        val catId = getFirstWord(catInfo)
        // генерация индекса для помещения в хеш-таблицу
        val bucketIndex = getBucketIndex(catId)
        // создаю список параметров кота
        val catProperties = getArguments(catInfo).split(" ")
        // data class для нового кота
        val catDataClass = Cat(catProperties[0], catProperties[1].toInt(), catProperties[2].toFloat())
        // пара <id> <data class>
        val newCat = Pair(catId, catDataClass)
        // если не нашлось такого же ключа
        if (keySearch(bucketIndex, catId) == -1) {
            CatHashTable[bucketIndex].add(newCat)
            print("$catId successfully added.\n")
        } else {
            System.err.print("Create: already exist.\n")
        }
    } else {
        print("Данные введены некорректно.\n")
    }
}
fun read(catId: String) {
    // для одного и того же id индекст будет одним и тем же
    val bucketIndex = getBucketIndex(catId)
    val pairIndex = keySearch(bucketIndex, catId)
    if(pairIndex != -1) {
        print("${CatHashTable[bucketIndex][pairIndex].first} --> ${CatHashTable[bucketIndex][pairIndex].second.colour}" +
                " ${CatHashTable[bucketIndex][pairIndex].second.age} ${CatHashTable[bucketIndex][pairIndex].second.weight}\n")
    } else {
        System.err.print("Read: not found.\n")
    }

}
fun update(catInfo: String) {
    // проверка данных на корректность
    if(isArgumentsCorrect(catInfo)) {
        // ключ - имя кота
        val catId = getFirstWord(catInfo)
        // для одного и того же id индекст будет одним и тем же
        val bucketIndex = getBucketIndex(catId)
        // создаю список параметров кота
        val catProperties = getArguments(catInfo).split(" ")
        // data class для нового кота
        val catDataClass = Cat(catProperties[0], catProperties[1].toInt(), catProperties[2].toFloat())
        // пара <id> <data class>
        val newCat = Pair(catId, catDataClass)
        // индекс пары с catId
        val pairIndex = keySearch(bucketIndex, catId)
        // если такой элемент есть
        if (pairIndex != -1) {
            CatHashTable[bucketIndex][pairIndex] = newCat
            print("$catId successfully updated.\n")
        } else {
            System.err.print("Update: not found.\n")
        }
    } else {
        print("Данные введены некорректно.\n")
    }
}
fun delete(catId: String) {
    // для одного и того же id индекст будет одним и тем же
    val bucketIndex = getBucketIndex(catId)
    // индекст пары с catId
    val pairIndex = keySearch(bucketIndex, catId)
    // если такой элемент есть
    if(pairIndex != -1) {
        CatHashTable[bucketIndex].remove(CatHashTable[bucketIndex][pairIndex])
        print("$catId removed successfully.\n")
    } else {
        System.err.print("Delete: not found.\n")
    }
}
fun where_weight(weight: String) {
    val weight = getArguments(weight).toFloat()
    for(arrayElement in CatHashTable) {
        for(linkedListElement in arrayElement) {
            if(linkedListElement.second.weight == weight) {
                print("${linkedListElement.first} --> ${linkedListElement.second.colour}" +
                        " ${linkedListElement.second.age} ${linkedListElement.second.weight}\n")
            }
        }
    }
}
fun readall() {
    for(arrayElement in CatHashTable) {
        for(linkedListElement in arrayElement) {
            print("${linkedListElement.first} --> ${linkedListElement.second.colour}" +
                    " ${linkedListElement.second.age} ${linkedListElement.second.weight}\n")
        }
    }
}