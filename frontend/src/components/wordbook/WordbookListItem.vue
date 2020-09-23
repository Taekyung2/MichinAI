<template>
<div>
    <v-card
    class="mx-auto"
    max-width="33.3%"
    outlined
    @click="selectedWordbook(wordbook)"
  >
    <v-list-item three-line>
      <v-list-item-content>
        {{wordbook.name}}
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