$("#login").submit ->
  $.ajax
    url: "/admin/login"
    method: "post"
    data: $("#login").serialize()
    dataType: "json"
    success: (ret) ->
      console.log ret
      window.location = "/admin"
  return false