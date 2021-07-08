package app.mvvm.architecture.sampleData

import app.mvvm.architecture.model.NewsItem
import java.time.OffsetDateTime

val SampleData.Companion.newsItems
    get() = listOf(
        NewsItem(
            "Mason Bailey",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus eu libero convallis placerat sit amet et metus. Vestibulum pretium nisl eget ullamcorper iaculis. Nunc nec libero luctus, vestibulum nisl non, semper justo. Vivamus diam justo, ultrices nec commodo vitae, fermentum non felis.",
            "",
            "https://picsum.photos/id/402/600",
            OffsetDateTime.now().minusMinutes(10),
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae tellus eu libero convallis placerat sit amet et metus. Vestibulum pretium nisl eget ullamcorper iaculis. Nunc nec libero luctus, vestibulum nisl non, semper justo. Vivamus diam justo, ultrices nec commodo vitae, fermentum non felis. Nunc mattis magna eu sodales vehicula. Donec quis tellus magna. Nullam laoreet dapibus mauris vel cursus. Proin eget odio augue. Sed ut luctus sapien. Duis eu augue sed libero cursus molestie sit amet id neque. Mauris sed tellus in libero lobortis ultricies eu nec nunc. Aenean tempus volutpat enim, et elementum sem ultricies a. Ut sit amet lectus nulla.",
        ),
        NewsItem(
            "Elianna Warner",
            "In ac erat et dolor venenatis consequat non ut tellus",
            null,
            "",
            "https://picsum.photos/id/328/600",
            OffsetDateTime.now().minusMinutes(27),
            null,
        ),
        NewsItem(
            "Frances Wright",
            "Sed feugiat magna vel lacus dapibus facilisis.",
            "Praesent eu lectus id magna vestibulum blandit. Proin at tincidunt mi. Pellentesque ut ante tristique, blandit quam sed, dignissim nulla. Ut sit amet facilisis nisl. Donec eget aliquam nisi.",
            "",
            null,
            OffsetDateTime.now().minusMinutes(96),
            "Pellentesque ut ante tristique, blandit quam sed, dignissim nulla. Ut sit amet facilisis nisl. Donec eget aliquam nisi. Cras convallis felis sed metus dignissim, at pulvinar risus lobortis. Sed vel tincidunt ligula, vitae dapibus erat. Pellentesque tristique turpis purus, vel sodales est auctor pretium. Vivamus et ornare neque, ultrices volutpat mi. Cras et consequat leo, at elementum nulla.",
        ),
    )