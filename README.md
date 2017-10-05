## Gilded Rose Refactoring Kata

Practicing [Emily Bache][emilybache]'s [Gilded Rose][gildedrose] kata

[gildedrose]: https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/master/Java
[emilybache]: https://github.com/emilybache

### Goal

The goal is to practice Kotlin, the language.

### Technical notes

  * The kata is not complete, it is missing to separate the behaviour for updating quality to the specific Item, rather than at the GildedRose element
  * Using a gradle wrapper (`gradlew`)
  * Keeping the data class `Item` but decorating it with the `MyItem`
    * `MyItem` encapsulates the business logic (e.g., increasing and decreasing quality, decreasing expiry date)
    * `MyItem` has a method to expose the decorated class `Item`
  * The distinct items have been coded using polymorphism plus a factory (in the `object ItemFactory`) that decides which item to create. This pattern violates the OCP, as adding a new Item type requires to modify (not to extend) the factory. I've decided that this level of complexity is enough for the case at hand 
  * Have created unit tests for the behaviour
  * The unit tests use method names with spaces (a kotlin feature), but only for test names. Not in production.


