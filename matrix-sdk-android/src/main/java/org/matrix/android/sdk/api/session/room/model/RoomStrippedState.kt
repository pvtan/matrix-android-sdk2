/*
 * Copyright 2021 The Matrix.org Foundation C.I.C.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.matrix.android.sdk.api.session.room.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * These are the same fields as those returned by /publicRooms, with a few additions: room_type, membership and is_encrypted.
 */
@JsonClass(generateAdapter = true)
data class RoomStrippedState(
        /**
         * Aliases of the room. May be empty.
         */
        @Json(name = "aliases")
        val aliases: List<String>? = null,

        /**
         * The canonical alias of the room, if any.
         */
        @Json(name = "canonical_alias")
        val canonicalAlias: String? = null,

        /**
         * The name of the room, if any.
         */
        @Json(name = "name")
        val name: String? = null,

        /**
         * Required. The number of members joined to the room.
         */
        @Json(name = "num_joined_members")
        val numJoinedMembers: Int = 0,

        /**
         * Required. The ID of the room.
         */
        @Json(name = "room_id")
        val roomId: String,

        /**
         * The topic of the room, if any.
         */
        @Json(name = "topic")
        val topic: String? = null,

        /**
         * Required. Whether the room may be viewed by guest users without joining.
         */
        @Json(name = "world_readable")
        val worldReadable: Boolean = false,

        /**
         * Required. Whether guest users may join the room and participate in it. If they can,
         * they will be subject to ordinary power level rules like any other user.
         */
        @Json(name = "guest_can_join")
        val guestCanJoin: Boolean = false,

        /**
         * The URL for the room's avatar, if one is set.
         */
        @Json(name = "avatar_url")
        val avatarUrl: String? = null,

        /**
         * Undocumented item.
         */
        @Json(name = "m.federate")
        val isFederated: Boolean = false,

        /**
         * Optional. If the room is encrypted. This is already accessible as stripped state.
         */
        @Json(name = "is_encrypted")
        val isEncrypted: Boolean?,

        /**
         * Optional. Type of the room, if any, i.e. m.space
         */
        @Json(name = "room_type")
        val roomType: String?,

        /**
         * The current membership of this user in the room. Usually leave if the room is fetched over federation.
         */
        @Json(name = "membership")
        val membership: String?
) {
    /**
     * Return the canonical alias, or the first alias from the list of aliases, or null.
     */
    fun getPrimaryAlias(): String? {
        return canonicalAlias ?: aliases?.firstOrNull()
    }
}
