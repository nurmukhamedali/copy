<template>
  <v-container fill-height>
    <v-layout align-center justify-center column>
      <v-alert
          :value="true"
          type="warning"

      >
        <h1 class="headline">Not Permitted</h1>
        <h2 class="subheading">Please go back to previous page. You don't have required permission</h2>
      </v-alert>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "NotPermitted"
}
</script>

<style scoped>

</style>