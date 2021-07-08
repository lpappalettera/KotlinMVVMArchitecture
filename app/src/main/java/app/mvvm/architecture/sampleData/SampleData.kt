package app.mvvm.architecture.sampleData

/**
 * Base sample data class which serves as a namespace of which the [Companion] object can be
 * extended to add model sample data.
 * ```
 * val SampleData.Companion.newsItems
 *     get() = listOf(...)
 * ```
 */
sealed class SampleData {
    companion object
}