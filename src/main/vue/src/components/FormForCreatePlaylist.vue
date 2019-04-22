<template>
    <div class="form">
        <div class="form-inputs">
            <div class="close" @click="closeWindow">
                Х
            </div>
            <label>
                Название плейлиста
                <input v-model="playlistName">
            </label>
            <label>
                Описание плейлиста
                <textarea v-model="playlistDescription"/>
            </label>
            <button @click="createPlaylist">
                Создать плейлист
            </button>
            {{message}}
        </div>
    </div>
</template>

<script>
import { createdPlaylist } from '@/api/rest/playlists.api';

export default {
    name: 'FormForCreatePlaylist',
    data: () => ({
        playlistName: '',
        playlistDescription: '',
        message: ''
    }),
    methods: {
        closeWindow() {
            this.$emit('close-window');
        },
        async createPlaylist() {
            if (await createdPlaylist({
                playlistName: this.playlistName,
                playlistDescription: this.playlistDescription
            })) {
                this.$emit('close-window');
            } else {
                this.message = 'Произошла ошибка';
            }
        }
    }
};
</script>

<style lang="scss" scoped>
    .form {
        position: fixed;
        top: 0;
        bottom: 0;
        right: 0;
        left: 0;
        background: rgba(0, 0, 0, 0.8);
        z-index: 1000;
        display: flex;
        align-items: center;
        justify-content: center;

        &-inputs {
            .close{
                cursor: pointer;
                margin-left: 100%;
                color: white;
                font-weight: 900;
                font-size: 30px;
            }
            width: 50%;
            label{
                color: white;
            }
            @mixin inputField() {
                outline: none;
                border: 1px solid transparent;
                width: 100%;
                max-width: 100%;
                min-width: 100%;
                padding: 10px;
                font-size: 15px;
                border-radius: 4px;
                margin-top: 10px;
                margin-bottom: 20px;
                &:focus {
                    border: 1px solid #0C0094;
                }
            }

            input {
                @include inputField;
            }

            textarea {
                @include inputField;
                max-height: 200px;
                min-height: 200px;
                height: 200px;
            }

            button{
                cursor: pointer;
                outline: none;
                font-size: 18px;
                border: none;
                transition: 300ms;
                min-width: 220px;
                padding: 14px;
                box-sizing: border-box;
                color: #0C0094;
                background: white;
                border-radius: 4px;
                box-shadow: 1px 2px 12px 0 rgba(0, 0, 0, 0.49);
                margin-left: 5px;

                &:hover {
                    transition: 300ms;
                    transform: scale(1.05);
                }
                &:active{
                    transform: scale(0.95);
                }
            }
        }
    }
</style>
