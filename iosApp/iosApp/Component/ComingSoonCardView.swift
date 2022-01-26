import SwiftUI
import shared

struct ComingSoonCardView: View {
    var movie: Movie

    var body: some View {
        ZStack(alignment: .topLeading) {
            AsyncImage(
                    url: URL(string: movie.imagePath.backdrop)
            ) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                case .success(let image):
                    image.resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: UIScreen.main.bounds.size.width)
                            .cornerRadius(20)
                case .failure:
                    Image(systemName: "photo")
                @unknown default:
                    EmptyView()
                }
            }

            Text(movie.title)
                    .padding(.top, 10)
                    .padding(.leading, 16)
                    .padding(.trailing, 16)
                    .lineLimit(1)
                    .font(.system(size: 16, weight: .bold, design: .rounded))
                    .foregroundColor(.white)
        }
    }

}
