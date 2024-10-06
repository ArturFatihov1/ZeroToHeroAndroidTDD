package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            if (max <= 0) throw IllegalStateException("max should be positive, but was $max")
            if (step > max) throw IllegalStateException("max should be more than step")
            if (max < min) throw IllegalStateException("max should be more than min")
        }

        override fun initial(number: String): UiState {
            return when (number.toInt()) {
                min -> UiState.Min(number)
                max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            return if (result + step <= max) {
                UiState.Base(result.toString())
            } else {
                UiState.Max(result.toString())
            }
        }

        override fun decrement(number: String): UiState {
            val result = number.toInt() - step
            return if (result - step >= min) {
                return UiState.Base(result.toString())
            } else {
                return UiState.Min(result.toString())
            }
        }


    }
}