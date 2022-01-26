import SwiftUI
import shared

struct MovieCardView: View {
    var movie: Movie

    var body: some View {
        ZStack {
            AsyncImage(
                    url: URL(string: movie.imagePath.poster)
            ) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                case .success(let image):
                    image.resizable()
                            .aspectRatio(0.7, contentMode: .fit)
                            .frame(width: UIScreen.main.bounds.size.width / 2)
                            .cornerRadius(20)
                case .failure:
                    Image(systemName: "photo")
                @unknown default:
                    EmptyView()
                }
            }
        }
    }
}