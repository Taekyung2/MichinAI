<template>
<div>
  <v-card
    class="conversation-item mx-auto"
   shaped
  >
    <v-card-title>
      {{conversation.id}}번째 영어 문장입니다.
    </v-card-title>

    <v-card-actions>

      <v-spacer></v-spacer>
      해석 
      <v-btn
        icon
        @click="show = !show"
      >
         <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text>
          여기는 해석입니다.  
        </v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</div>

</template>

<script>
export default {
    name: 'ConversationListItem',
    props: {
      conversation: Object,
    },
    data: () => ({
      show: false,
      colors: [
          'indigo',
          'warning',
          'pink darken-2',
          'red lighten-1',
          'deep-purple accent-4',
      ],
    }),
}
</script>

<style>
.conversation-item{
  margin: 10px 0;
}
</style>