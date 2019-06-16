require: dict/kinder.csv
  name = kinder

require: dict/zoo.csv
  name = zoo

require: dict/family.csv
  name = family

require: dict/month.csv
  name = month

patterns:

  $kinder = $entity<kinder>

  $zoo = $entity<zoo>

  $family = $entity<family>

  $month = $entity<month>

