<template>
<div class="outgoing_msg">
<div class="sent_msg">
    <p>{{chat.msg}}</p>
    <span class="time_date"> 11:01 AM    |    June 9</span> </div>
</div>
</template>

<script>
export default {
    name: 'sendMsg',

    props: {
        chat: {
            type: Object,
            required: true,
        }
    },
}
</script>
// 05728f
<style>
.sent_msg p {
  background: #7DB3D9 none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; }
.sent_msg {
  float: right;
  width: 46%;
}
</style>