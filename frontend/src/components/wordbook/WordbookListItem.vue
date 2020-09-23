<template>
<div>
    <v-card
    class="mx-auto"
    max-width="344"
    outlined
    @click="selectedWordbook(wordbook)"
  >
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="headline mb-1">{{wordbook.id}}번째, 단어장 이름</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

  </v-card>

</div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'WordbookListItem',
    props: {
      wordbook: Object,
    },
    methods: {
      ...mapActions(['selectedWordbook']),
    }
}
</script>

<style>

</style>