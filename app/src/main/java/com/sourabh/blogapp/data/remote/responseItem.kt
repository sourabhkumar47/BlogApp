package com.sourabh.blogapp.data.remote

import kotlin.collections.Collection

data class responseItem(
    val _links: Links,
    val aioseo_notices: List<Any>,
    val author: Int,
    val categories: List<Int>,
    val class_list: List<String>,
    val comment_status: String,
    val content: Content,
    val date: String,
    val date_gmt: String,
    val excerpt: Excerpt,
    val featured_media: Int,
    val format: String,
    val guid: Guid,
    val id: Int,
    val jetpackrelatedposts: List<Any>,
    val jetpack_featured_media_url: String,
    val jetpack_likes_enabled: Boolean,
    val jetpack_publicize_connections: List<Any>,
    val jetpack_sharing_enabled: Boolean,
    val jetpack_shortlink: String,
    val link: String,
    val meta: Meta,
    val modified: String,
    val modified_gmt: String,
    val ping_status: String,
    val slug: String,
    val status: String,
    val sticky: Boolean,
    val tags: List<Int>,
    val template: String,
    val title: Title,
    val type: String
)

data class About(
    val href: String
)

data class Author(
    val embeddable: Boolean,
    val href: String
)

data class Collection(
    val href: String
)

data class Content(
    val `protected`: Boolean,
    val rendered: String
)

data class Cury(
    val href: String,
    val name: String,
    val templated: Boolean
)

data class Excerpt(
    val `protected`: Boolean,
    val rendered: String
)
data class Guid(
    val rendered: String
)
data class ImageGeneratorSettings(
    val enabled: Boolean,
    val template: String
)

data class JetpackSocialOptions(
    val image_generator_settings: ImageGeneratorSettings,
    val version: Int
)

data class Links(
    val about: List<About>,
    val author: List<Author>,
    val collection: List<CollectionLink>,
    val curies: List<Cury>,
    val predecessorversion: List<PredecessorVersion>,
    val replies: List<Reply>,
    val self: List<Self>,
    val versionhistory: List<VersionHistory>,
    val wpattachment: List<WpAttachment>,
    val wpfeaturedmedia: List<WpFeaturedmedia>,
    val wpterm: List<WpTerm>
)
data class CollectionLink(
    val href: String
)
data class Meta(
    val _jetpack_dont_email_post_to_subs: Boolean,
    val _jetpack_memberships_contains_paid_content: Boolean,
    val _jetpack_memberships_contains_paywalled_content: Boolean,
    val _jetpack_newsletter_access: String,
    val _jetpack_newsletter_tier_id: Int,
    val footnotes: String,
    val jetpack_post_was_ever_published: Boolean,
    val jetpack_publicize_feature_enabled: Boolean,
    val jetpack_publicize_message: String,
    val jetpack_social_options: JetpackSocialOptions,
    val jetpack_social_post_already_shared: Boolean
)

data class PredecessorVersion(
    val href: String,
    val id: Int
)

data class Reply(
    val embeddable: Boolean,
    val href: String
)

data class Self(
    val href: String,
    val targetHints: TargetHints
)

data class TargetHints(
    val allow: List<String>
)


data class VersionHistory(
    val count: Int,
    val href: String
)

data class WpAttachment(
    val href: String
)

data class WpFeaturedmedia(
    val embeddable: Boolean,
    val href: String
)

data class WpTerm(
    val embeddable: Boolean,
    val href: String,
    val taxonomy: String
)