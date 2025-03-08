export async function getResponse(url) {
    return `${url}?37`
}

export function getResponseOptional(url) {
    if (url.endsWith(".io")) {
        return null
    }

    return Promise.resolve(`${url}?23`)
}
