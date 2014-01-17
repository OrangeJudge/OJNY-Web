$("#problem-info").submit ->
  postData = $("#problem-info").serialize()
  $.ajax
    url: "/admin/problem/add"
    data: postData
    type: "post"
    dataType: "json"
    success: (ret) ->
      console.log ret

  return false