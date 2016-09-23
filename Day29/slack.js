$(document).ready(function(){
var msg = " ";
var myToken = getSlackToken();

publicChannel();
privateChannel();
// individualIM();

function publicChannel(){
// GET CHANNEL NAMES
   $.ajax("https://slack.com/api/channels.list", {
     data: {
       token: myToken
     },
   method: "POST"
   }).then(function(channelsList){
       for(var count = 0; count < channelsList.channels.length; count++)
       {
           var newChannelBtn = $("<input type='radio' name='channel' value="+channelsList.channels[count].name+"> " + channelsList.channels[count].name + "</input><br>");
           $("#public").append(newChannelBtn);
       }
       console.log(channelsList);
   })
};
function privateChannel() {
   $.ajax("https://slack.com/api/groups.list", {
     data: {
       token: myToken
     },
   method: "POST"
 }).then(function(groupsList){
       for(var count = 0; count < groupsList.groups.length; count++)
       {
           var newChannlBtn = $("<input type='radio' name='channel' value="+groupsList.groups[count].name+"> " + groupsList.groups[count].name + "</input><br>");
           $("#private").append(newChannlBtn);
       }
       console.log(groupsList);
   })
 };
// function individualIM(){
//   $.ajax("https://slack.com/api/im.list", {
//     data: {
//       token: myToken
//     },
//   method: "POST"
// }).then(function(imList){
//       for(var count = 0; count < imList.length; count++)
//       {
//           var newChannlBtn = $("<input type='radio' name='channel' value="+imList.im[count].name+"> " + imList.im[count].name + "</input><br>");
//           $("#myIm").append(newChannlBtn);
//       }
//       console.log(imList);
//   })
// };
function postMessage(msg, myChannel) {
  $.ajax("https://slack.com/api/chat.postMessage", {
  data: {
    token: myToken,
    channel: myChannel,
    text: msg,
    username: "Cornholio"
  },
  method: "POST"
})

};

jQuery('#toggle').on('click', function(event) {
            jQuery('#private').toggle('show');
       });
})

$("#post").click(function() {
  var text = $("#message-box").val();
  var channel = $('input[name="channel"]:checked').val();
  postMessage(text, channel)
  $("#message-box").val('');
})

$('#message-box').keypress(function (e) {
var key = e.which;
if(key == 13)  // the enter key code
 {
   $("#post").click();
   return false;
 }
});


// $.ajax("https://slack.com/api/auth.test?token=xoxp-63743851541-69472081029-82792731585-99138ac8c5e89f924eb491f6b8921b46&pretty=1", {
//   method: "POST"
// }).then(function(result) {
//   console.log(result);
