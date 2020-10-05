<template>
    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <span
          v-bind="attrs"
          v-on="on"
          class="text-decoration-line-underline highlight"
          v-text="msg[0]"
        />
        
      </template>
      <span v-html="msg[1]"></span>
    </v-tooltip>
</template>

<script>
export default {
    name: 'correction',
    props: {
        msg: Array
    }
}
</script>

<style>
.highlight {
    background-color: var(--main-sub-color);
    color: var(--font-base-color);
}
</style>