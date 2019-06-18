<template>
    <div class="playlist-track">
        <PlaylistTrackButton
            :id="id"
            :name="trackName"
            :index="index"
            @click.native="clickOnTrack"
        />
        <div v-if="canDeleteTrack" class="minus" @click="deleteTrack">
            X
        </div>
    </div>
</template>
<script>
import PlaylistTrackButton from './PlaylistTrackButton';
export default {
    name: 'PlaylistTrack',
    components: { PlaylistTrackButton },
    props: {
        trackName: {
            type: String,
            required: true,
            default: ''
        },
        index: {
            type: Number,
            default: 0
        },
        id: {
            type: Number,
            default: 0
        },
        canDeleteTrack: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        clickOnTrack: function () {
            this.$emit('click-on-track', this.id);
        },
        deleteTrack: function () {
            this.$emit('delete-track', this.id);
        }
    }
};
</script>

<style lang="scss" scoped>
    .playlist-track {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin: 10px 0;
        .minus {
            cursor: pointer;
            font-weight: 900;
        }
        .delete-track{
            height: 25px;
            opacity: .5;
            cursor: pointer;
            &:hover{
                transition: 500ms;
                opacity: 1;
            }
        }
    }
</style>
