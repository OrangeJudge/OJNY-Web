$("#submit-form").submit ->
  console.log $("#submit-form").serialize()
  return false