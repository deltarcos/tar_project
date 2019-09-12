<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ใบนัดทั้งหมด</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="15">
        <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "ViewAppointmentsData",
  data() {
    return {
      headers: [
        { text: "รหัสใบนัด", value: "id" },
        { text: "แพทย์", value: "createdBy.name" },
        {
          text: "ผู้ป่วย",
          align: "left",
          sortable: false,
          value: "appmPatient.name"
        },
        { text: "วันที่นัด", value: "appmDate" },
        { text: "เวลานัด", value: "duration.name" },
        { text: "ห้องตรวจ", value: "room.title" },
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getAppointments() {
      http
        .get("/appointments")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getAppointments();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getAppointments();
  }
};
</script>
