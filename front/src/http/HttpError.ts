import type { AxiosError } from 'axios'

interface ErrorResponse {
  code?: string;
  message?: string;
}

export default class HttpError {
  private readonly code: string
  private readonly message: string

  constructor(e: AxiosError) {
    const responseData = e.response?.data as ErrorResponse

    this.code = responseData?.code ?? '500'
    this.message = responseData?.message ?? '네트워크 상태가 좋지 않습니다.'
  }

  public getMessage(): string {
    return this.message
  }
}
