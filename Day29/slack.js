
$(document).ready(function(){
var slackToken = getSlackToken();

var msg=" ";


  $.ajax("https://slack.com/api/groups.list", {
       data: {
           token: slackToken

       },
  method: "POST"
  }).then(function(groupsList){
      for(var count = 0; count < groupsList.groups.length; count++)
      {
          var newChannelBtn = $("<input type='radio' name='channel' value="+groupsList.groups[count].name+"> " + groupsList.groups[count].name + "</input><br>");
          $("#private").append(newChannelBtn);
      }
      console.log(groupsList);
  });



// GET CHANNEL NAMES
  $.ajax("https://slack.com/api/channels.list", {
       data: {
           token: slackToken

       },
  method: "POST"
  }).then(function(channelsList){
      for(var count = 0; count < channelsList.channels.length; count++)
      {
          var newChannelBtn = $("<input type='radio' name='channel' value="+channelsList.channels[count].name+"> " + channelsList.channels[count].name + "</input><br>");
          $("#public").append(newChannelBtn);
      }
      console.log(channelsList);
  });

function postButton(msg,channel){
$.ajax("https://slack.com/api/chat.postMessage", {
 data: {
    token: slackToken,
   channel: channel,
    username:"JoshuaFraney",
   text: msg
 },
  method: "POST"
})

}
$("#post").click(function() {
var text = $("#message-box").val();
var channel=$('input[name="channel"]:checked').val();
postButton(text,channel);
$("#message-box").val('');
})

$('#message-box').keypress(function (e) {
var key = e.which;
if(key == 13)  // the enter key code
{
  $("#post").click();
  return false;
}
})

     jQuery('#toggle').on('click', function(event) {
            jQuery('#prvte').toggle('show');

       });
})
