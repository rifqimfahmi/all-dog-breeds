package com.rifqimfahmi.alldogbreeds.util

import android.support.v4.app.FragmentManager
import com.rifqimfahmi.alldogbreeds.dialog.CommonLoadingDialog
import java.util.*

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

class CommonUtils {

    companion object {
        val breeds = arrayOf(
                "affenpinscher",
                "african",
                "airedale",
                "akita",
                "appenzeller",
                "basenji",
                "beagle",
                "bluetick",
                "borzoi",
                "bouvier",
                "boxer",
                "brabancon",
                "briard",
                "bulldog",
                "bullterrier",
                "cairn",
                "chihuahua",
                "chow",
                "clumber",
                "collie",
                "coonhound",
                "corgi",
                "dachshund",
                "dane",
                "deerhound",
                "dhole",
                "dingo",
                "doberman",
                "elkhound",
                "entlebucher",
                "eskimo",
                "germanshepherd",
                "greyhound",
                "groenendael",
                "hound",
                "husky",
                "keeshond",
                "kelpie",
                "komondor",
                "kuvasz",
                "labrador",
                "leonberg",
                "lhasa",
                "malamute",
                "malinois",
                "maltese",
                "mastiff",
                "mexicanhairless",
                "mountain",
                "newfoundland",
                "otterhound",
                "papillon",
                "pekinese",
                "pembroke",
                "pinscher",
                "pointer",
                "pomeranian",
                "poodle",
                "pug",
                "pyrenees",
                "redbone",
                "retriever",
                "ridgeback",
                "rottweiler",
                "saluki",
                "samoyed",
                "schipperke",
                "schnauzer",
                "setter",
                "sheepdog",
                "shiba",
                "shihtzu",
                "spaniel",
                "springer",
                "stbernard",
                "terrier",
                "vizsla",
                "weimaraner",
                "whippet",
                "wolfhound"
        )

        fun showLoadingDialog(fm: FragmentManager, message: String?): CommonLoadingDialog {
            val commonDialog: CommonLoadingDialog = CommonLoadingDialog.newInstance(message)
            commonDialog.show(fm, CommonLoadingDialog.TAG)
            return commonDialog
        }

        fun uppercaseTheFirstLetter(string: String): String {
            return "${string.substring(0, 1).toUpperCase()}${string.substring(1)}"
        }

        fun getBreedFromLink(link: String): String {
            var modLink = link.substring(30)
            val lastPathIndex = modLink.indexOf("/")

            modLink = modLink.substring(0, lastPathIndex)
            return modLink
        }

        fun getRandomDogMemeOffset(): Int {
            return (0..4998).random()
        }
    }
}

fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) + start