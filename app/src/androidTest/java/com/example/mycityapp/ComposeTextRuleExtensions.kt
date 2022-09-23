package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

/**
 * Finds a semantics node with the given string resource id.
 *
 * The [onNodeWithText] finder provided by compose ui test API, doesn't support usage of
 * string resource id to find the semantics node. This extension function accesses string resource
 * using underlying activity property and passes it to [onNodeWithText] function as argument and
 * returns the [SemanticsNodeInteraction] object.
 */
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))

/**
 * Finds a semantics node from the content description with the given string resource id.
 *
 * The [onNodeWithContentDescription] finder provided by compose ui test API, doesn't support usage
 * of string resource id to find the semantics node from the node's content description.
 * This extension function accesses string resource using underlying activity property
 * and passes it to [onNodeWithContentDescription] function as argument and
 * returns the [SemanticsNodeInteraction] object.
 */
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>
        .onNodeWithContentDescriptionForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithContentDescription(activity.getString(id))

/**
 * Finds a semantics node from the content description with the given string resource id.
 *
 * The [onNodeWithTag] finder provided by compose ui test API, doesn't support usage of
 * string resource id to find the semantics node from the node's test tag.
 * This extension function accesses string resource using underlying activity property
 * and passes it to [onNodeWithTag] function as argument and
 * returns the [SemanticsNodeInteraction] object.
 */
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>
        .onNodeWithTagForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithTag(activity.getString(id))