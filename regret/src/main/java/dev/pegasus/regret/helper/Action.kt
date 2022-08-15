package dev.pegasus.regret.helper

class Action(val key: String, val value: Any) {

    override fun equals(other: Any?): Boolean {
        return other is Action && other.key == key && other.value == value
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}