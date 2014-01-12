$("#problem-info").submit ->
  console.log $("#problem-info").serialize()
  return false