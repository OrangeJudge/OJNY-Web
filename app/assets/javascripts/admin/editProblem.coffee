$("#problem-info").submit ->
  postData = $("#problem-info").serialize()
  $.ajax
    url: "/admin/problem/1/edit"
    data: postData
    type: "post"
    dataType: "json"
    success: (ret) ->
      console.log ret
      if ret["error"] == 0
        alert "saved!"
        location.reload()
      else
        alert ret["message"]

  return false