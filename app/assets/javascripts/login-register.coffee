Messenger.options = {
  extraClasses: 'messenger-fixed messenger-on-top',
  theme: 'flat'
}

$("#login-form").submit ->
  msg = Messenger().post
    message: 'Sending login request.'
    type: 'info'
  $.ajax
    url: "/member/login"
    type: "post"
    data: $("#login-form").serializeArray()
    success: ->
      msg.hide()
      msg = Messenger().post
        message: 'Success.'
        type: 'success'
  return false