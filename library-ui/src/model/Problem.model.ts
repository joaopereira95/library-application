import { ProblemError } from "./ProblemError.model";

export class Problem {
    title: string = '';
    status: number = 0;
    detail: string = '';
    type: string = '';
    errors: ProblemError[] = []
}