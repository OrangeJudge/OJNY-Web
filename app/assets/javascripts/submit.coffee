Messenger.options = {
  extraClasses: 'messenger-fixed messenger-on-top',
  theme: 'flat'
}

msg = {}

displayMessage = (content, type) ->
  if "hide" of msg
    msg.hide()
  msg = Messenger().post
    message: content
    type: type

$("#submit-form").submit ->
  $("#submit-button").prop('disabled', true)
  displayMessage("Submitting", "info")
  submitData = $("#submit-form").serialize()
  $.ajax
    url: "/submit"
    type: "post"
    data: submitData
    dataType: "json"
    success: (ret) ->
      console.log ret
      if ret["error"] == 0
        displayMessage("Success", "success")
        $("#submit-button").prop('disabled', false)
        setTimeout ( ->
          window.location.reload()
        ), 1000
      else
        displayMessage(ret["message"], "error")
        $("#submit-button").prop('disabled', false)
    error: ->
      $("#submit-button").prop('disabled', false)
  return false