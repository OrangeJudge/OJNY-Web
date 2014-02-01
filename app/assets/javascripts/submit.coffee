$("#submit-form").submit ->
  submitData = $("#submit-form").serialize()
  $.ajax
    url: "/submit"
    type: "post"
    data: submitData
    dataType: "json"
    success: (ret) ->
      console.log ret
  return false