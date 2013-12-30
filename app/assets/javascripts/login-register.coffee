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

successHandler = (ret) ->
  msg.hide()
  error = 0
  errorMsg = ""
  if "error" of ret
    if ret["error"] > 0
      error = ret["error"]
      errorMsg = ret["message"]
  else
    error = 1
    errorMsg = "Unknown error"
  if error > 0
    displayMessage(errorMsg, "error")
  else
    displayMessage("Success", "success")

errorHandler = ->
  displayMessage(
    "Connection error. Please try again later.",
    "error"
  )

$("#login-form").submit ->
  displayMessage(
    "Sending login request.",
    "info"
  )
  $.ajax
    url: "/member/login"
    type: "post"
    data: $("#login-form").serializeArray()
    success: successHandler
    error: errorHandler
  return false

$("#register-form").submit ->
  if $("#gender").val() == null || $("#gender").val() == "-1"
    displayMessage("Please select your gender.", "error")
    return false
  if $("#password").val() != $("#password-confirm").val()
    displayMessage("Passwords are different. Please retype the passwords.", "error")
    return false
  displayMessage(
    "Sending registration request.",
    "info"
  )
  $.ajax
    url: "/member/register"
    type: "post"
    data: $("#register-form").serializeArray()
    success: successHandler
    error: errorHandler
  return false