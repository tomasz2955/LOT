export function formatDate(stringDate: string): string {
    const date = new Date(stringDate)
    return date.toLocaleDateString("en-GB", {
        day: "numeric",
        hour: "numeric",
        minute: "numeric",
        month: "long",
        year: "numeric"
    })
}
